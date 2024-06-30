import React from 'react';

function ParentComponent() {
  const handleClick = () => {
    alert('ボタンがクリックされました');
  };

  return <ChildComponent onClickHandler={handleClick} />;
}

function ChildComponent(props) {
  return (
    <button onClick={props.onClickHandler}>ボタンをクリックしてください</button>
  );
}

const ProfileCard = ({name, age, sex, profession}) => {
  return (
    <div className="card">
      <div className="card-content">
        <h3>Card</h3>
        <p>Name: {name}</p>
        <p>Age: {age}</p>
        <p>Sex: {sex}</p>
        <p>Profession: {profession}</p>
      </div>
    </div>
  )
}

// 条件付きレンダリング
function WelcomeMessage({isLoggedIn}) {
  if (isLoggedIn) {
    return <h1>Welcome back!</h1>;
  } else {
  return <h1>Please sign up.</h1>;
}
}

function UserGreeting() {
  return <h1>Welcome back!</h1>;
}

function GuestGreeting() {
  return <h1>Please sign up.</h1>;
}

function Greeting({isLoggedIn}) {
  return (
    <div>
      {isLoggedIn && <UserGreeting />}
      {!isLoggedIn && <GuestGreeting />}
    </div>
  );
}

function ConditionalGreeting({isLoggedIn}) {
  return (
    <div>
      {isLoggedIn ? <UserGreeting /> : <GuestGreeting />}
    </div>
  );
}

const programmingLanguages = [
  {
    id: "1",
    name: "JavaScript",
    description: "JavaScript is a programming language that is used to create interactive effects within web browsers.",
    imgUrl: "https://upload.wikimedia.org/wikipedia/commons/6/6a/JavaScript-logo.png"
  },
  {
    id: "2",
    name: "Python",
    description: "Python is a programming language that lets you work quickly and integrate systems more effectively.",
    imgUrl: "https://upload.wikimedia.org/wikipedia/commons/c/c3/Python-logo-notext.svg"
  },
  {
    id: "3",
    name: "Ruby",
    description: "Ruby is a dynamic, open source programming language with a focus on simplicity and productivity.",
    imgUrl: "https://upload.wikimedia.org/wikipedia/commons/7/73/Ruby_logo.svg"
  }
];

function ProgrammingCard(Props) {
  const {name, description, imgUrl} = Props;

  return (
    <div className="cardContainer">
      <div className="imgBox">
        <img src={imgUrl} alt={name} />
      </div>
      <div className="programingName">
        <h2>{name}</h2>
      </div>
      <div className="programingDescription">
        <p>{description}</p>
      </div>
    </div>
  )
}

const App: React.FC = () => {
  return (
    <div>
      <h1>Programming Launguages List</h1>
      <div className="cards">
        {
          programmingLanguages.map((info) => (
            <ProgrammingCard
              key={info.id}
              name={info.name}
              description={info.description}
              imgUrl={info.imgUrl}
            />
          ))
        }
      </div>
    </div>
  )
};

export default App;

