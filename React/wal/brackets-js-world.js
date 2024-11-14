// 波括弧野中でJavaScriptを使う
export default function TodoList() {
    const name = 'Gregorio Y. Zara';
    return (
        <h1>{name}'s To Do List</h1>
    )
}

const today = new Date();

function formatDate(date) {
    return new Intl.DateTimeFormat(
        'en-US',
        {weekday: 'long'}
    ).format(date);
}

// 関数の呼び出しも可能
function TodoList2() {
    return (
        <h2>Today is {formatDate(today)}</h2>
    )
}