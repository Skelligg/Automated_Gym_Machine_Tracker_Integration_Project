import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import psycopg2

# preparing the data
conn = psycopg2.connect(host='10.134.178.163',user='postgres',password='IP3dbP@ss',database='postgres')

query = 'select g.user_id as gym_goer_id, g.gender, s.start_session, s.end_session, extract(epoch from (s.end_session - s.start_session)) /60 as session_duration from session s join gym_goer g  on s.user_id = g.user_id;'

df = pd.read_sql(query,conn)

# print(df.head())
conn.close()

df['start_session'] = pd.to_datetime(df['start_session'])
df['end_session'] = pd.to_datetime(df['end_session'])

# calculating the days between sessions for each gymGoer
df['days_since_last_session'] = df.groupby('gym_goer_id')['start_session'].diff().dt.days
# putting into the NA a 0
df['days_since_last_session'] = df['days_since_last_session'].fillna(0)

# visits once a week
dedication = 7
# people that skipped more than 30 days are more likely to give up
dropout = 30


# categorizing
user_summary = df.groupby(['gym_goer_id','gender']).agg(total_sessions=('gym_goer_id','count'),
                                                        avg_days_between_sessions=('days_since_last_session','mean'),max_gap_between_sessions=('days_since_last_session','max'),avg_session_duration=('session_duration','mean'),last_session=('start_session','max')).reset_index()
#marking who is most likely to give up
today = pd.Timestamp.today()
user_summary['is_active'] = (today - user_summary['last_session']).dt.days <= dropout
user_summary['weeks_activity'] = ((user_summary['last_session'] - df['start_session'].min()).dt.days/7).clip(lower=1)
user_summary['session_per_week']= user_summary['total_sessions']/user_summary['weeks_activity']
user_summary['user_dedication'] = np.where((user_summary['avg_days_between_sessions'] <= dedication) & user_summary['is_active'],'Dedicated','Likely to Give up')

# analyze by gender
gender_summary = user_summary.groupby('gender').user_dedication.value_counts(normalize=True).unstack()*100

# saving the data to JSON file
# gender_summary.to_json('gender_summary.json',orient='table')
gender_summary.to_json('../../JavaSpring/src/main/resources/dataAI/genderPrediction/gender_summary.json', orient='table')


 # plot it
plt.figure(figsize=(10,8))
gender_summary.plot(kind='bar',stacked=True,colormap='coolwarm',figsize=(10,8))
plt.title('Dedication vs Dropout by gender')
plt.xlabel('Gender')
plt.ylabel('Percentage')
plt.legend(title='Behavior Category')
plt.show()
