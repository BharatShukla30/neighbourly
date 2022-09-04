import sqlite3

def updateUsers(cur, args):
    '''
    this function adds/update data into a table
    '''
    try:
        res = cur.execute("INSERT INTO users (id, latitude, longitude) VALUES (?, ?, ?)",args)
        print("Operation successful")
    except Exception as e:
        print("Something went wrong {}".format(e))

def checkUidAvailability(cur, uid):
    '''
    this function checks if a uid is available in the database
    '''
    try:
        res = cur.execute("SELECT id FROM users WHERE id = '{}'".format(uid))
        if res.fetchone() is None:
            return True
        return False
    except Exception as e:
        print("Something went wrong {}".format(e))



