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

Page = namedtuple('Page', ('pageId', 'title', 'description', 'content', 'url', 'authorId'))

page1 = Page(1, "Hello World", "Explain how hello world works", "<div>Hello World!!! Today we will learn about logging</div>", "/learn/hello-world", 1)
page2 = Page(2, "I/O Streams", "Cover input and output streams.", "<p>Input and output streams are important to understand. They allow us to input from and output to OS.</p>", "/learn/io-streams", 1)
page3 = Page(3, "Careers", "Information regarding job openings", "<h2>We currently have several job openings!</h2>", "/uk/careers/openings", 4)
page4 = Page(4, "Contact", "Contact information", "<form>Use this form to contact us!</form>", "/contact", 4)
page5 = Page(5, "Your dream job", "Learn how to land your dream job.", "<p>Finding your dream job comes down to the intersection of your likes, your talents, and other's needs</p>", "/blog/dream-job", 4)
page6 = Page(6, "Cooking with Joe - Cake", "Learn how to prepare cake with our guest Joe.", "<p>Hi, my name is Joe and we will learn how to bake cakes. Lets go over the ingredients you will need.</p>", "/learn/cooking/cake-with-joe", 1)
# page関係
pageRelation = [page1, page2, page3, page4, page5, page6]

select = lambda predicateF, relation: filter(predicateF, relation)
f = lambda predicateF: lambda rTuple: predicateF(rTuple)

ex1 = select(f(lambda rTuple: len(rTuple.userName) >= 10), userRelation)
print(list(ex1))

ex2 = select(f(lambda rTuple: rTuple.countryCode == 1 and (rTuple.userType == 'admin' or rTuple.userType == 'subscriber')), userRelation)
print(list(ex2))

#誕生日が３〜5月のユーザーを取得する。datetimeを使って月を取得する
ex3 = select(f(lambda rTuple: datetime.datetime.strptime(rTuple.dateOfBirth, "%m/%d/%Y").month in [3, 4, 5]), userRelation)
print(list(ex3))

ex4 = select(f(lambda rTuple: rTuple.userId in [1, 3, 4, 5, 6]), userRelation)
print(list(ex4))
