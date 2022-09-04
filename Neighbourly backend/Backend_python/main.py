from pickle import LONG_BINGET
from fastapi import FastAPI
import sqlite3
from sqlite3 import Error
import Repository.users as users

def createConnection(path):
    connection = None
    try:
        connection = sqlite3.connect(path)
        print("Connection to SQLite DB successful")
    except Error as e:
        print(f"The error '{e}' occurred")

    return connection

def create(path):
    '''
    TODO: devise a way where user can create table using this function
    '''
    con = sqlite3.connect(path)
    cur = con.cursor()
    cur.execute("CREATE TABLE users(id, latitude, longitude)")

def register(cur):
    '''
    Register a new user
    '''
    while True:
        uid = input("Enter a username:")
        if users.checkUidAvailability(cur, uid):
            break
        print("Enter a unique username!\n")
    
    latitude = input("Latitude: ")
    longitude = input("Longitude: ")
    users.updateUsers(cur, (uid, latitude, longitude))
    con.commit()

if __name__ == "__main__":
    #con is the connection to the DB    
    con = createConnection(r'C:\Users\bhara\OneDrive\Desktop\neighbourly\dbuno.db')
    #cur lets you navigate the database
    cur = con.cursor()
    #register(cur)
    
    res = cur.execute("Select * from users")
    print(res.fetchall())
    #print(users.checkUidAvailability(cur, uid))
    #fetch(con)

