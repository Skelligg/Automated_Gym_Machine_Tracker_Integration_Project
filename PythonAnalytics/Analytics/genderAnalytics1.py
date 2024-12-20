import pandas as pd
import psycopg2
import seaborn as sns
import matplotlib.pyplot as plt


connection = psycopg2.connect(host='10.134.178.163',user='postgres',password='IP3dbP@ss',database='postgres')

query = 'select g.user_id as gym_goer_id, g.gender, s.start_session, s.end_session, extract(epoch from (s.end_session - s.start_session)) /60 as session_duration from session s join gym_goer g  on s.user_id = g.user_id;'

df = pd.read_sql(query,connection)

# print(df.head())
connection.close()

df['start_session'] = pd.to_datetime(df['start_session'])
df['end_session'] = pd.to_datetime(df['end_session'])

# summarise the data by gender
summary = df.groupby('gender').agg(total_sessions=('gym_goer_id', 'count'),avg_session_duration=('session_duration', 'mean'))

# showing the overall number of sesssion by gender
plt.figure(figsize = (10,6))
sns.barplot(data=summary,x='gender',y='total_sessions',hue='gender',palette='Set1')
plt.title('Total Sessions by Gender')
plt.xlabel('Gender')
plt.ylabel('Number of Sessions')
plt.show()


# analyzing the most hours the people worked out
time_distribution = df['start_session'].dt.hour.value_counts().sort_index()
plt.figure(figsize = (10,6))
sns.barplot(x=time_distribution.index,y=time_distribution.values,palette='coolwarm')
plt.title('Gym Activity Distribution by Hour')
plt.xlabel('Hour of Day')
plt.ylabel('Number of Sessions')
plt.show()

