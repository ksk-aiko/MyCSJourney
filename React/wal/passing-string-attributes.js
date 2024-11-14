export default function Avatar() {
    const avatar = https://i.imgur.com/example.jpg;
    const description = "Gregorio Y. Zara";

    return (
        <img
           className="avatar" 
           src = {avatar}
           alt = {description}
        />
    );
}