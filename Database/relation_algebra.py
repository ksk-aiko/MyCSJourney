import datetime
from collections import namedtuple

def hashPassword(passwordstring):
    hash = 5381
    for x in passwordstring:
        hash = ((hash << 5) + hash) + ord(x)
    return hash & 0xFFFFFFFF

User = namedtuple('User', ('userId', 'userName', 'email', 'password', 'dateOfBirth', 'countryCode', 'userType'))

user1 = User(1, "tommy434", "tommy434@example.com", hashPassword("c387c2fb3e99cb5"), "05/25/1982", 1 ,"admin")
user2 = User(2, "doorpink9", "doors2doors@example.com", hashPassword("386449c7dba62e"), "02/16/1979", 1 ,"default")
user3 = User(3, "parkriderz", "parkriderz@example.com", hashPassword("79879ce55ebf7e0"), "10/18/1990", 1, "subscriber")
user4 = User(4, "derkknight", "derkknight93@example.com", hashPassword("9f340036bcb891"), "08/20/1993", 44, "editor")
user5 = User(5, "soulstart234", "standby4r@example.com", hashPassword("15c6647e8d2649"), "09/04/2000", 44, "default")
user6 = User(6, "estrologyturnout", "cosmoticsrty@example.com", hashPassword("55235311007445"), "01/02/1999", 44, "subscriber")

userRelation = [user1, user2, user3, user4, user5, user6]

select = lambda predicateF, relation : filter(predicateF, relation)

f = lambda predicate: lambda rTuple: predicate(rTuple)

print("---例２---")
ex2 = select(f(lambda rTuple: rTuple.countryCode == 44), userRelation)