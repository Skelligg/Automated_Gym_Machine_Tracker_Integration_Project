import pandas as pd
import psycopg2
import seaborn as sns
import matplotlib.pyplot as plt


connection = psycopg2.connect(host='10.134.178.163',user='postgres',password='IP3dbP@ss',database='postgres')

query = 'select user_id, gender from gym_goer;'
df = pd.read_sql(query,connection)
connection.close()
# print(df.head())

gender_counts = df['gender'].value_counts()

plt.figure(figsize = (8,6))
sns.barplot(x=gender_counts.index,y=gender_counts.values,palette='rocket')


plt.xlabel('Gender')
plt.ylabel('Number of GymGoers')
plt.title('Gym Gender Distribution by Gender')

plt.show()
