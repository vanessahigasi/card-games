/**
// Example component that displays another component (title)
//Notice every React component needs to have one root element
//that's why we put everything in a <div>
 */

const App = (props) => (
    <div>
        <GamesList title= "Game List"/>
        <Article title="This is an Article" text="This is the text in the paragraph"/>
        <Link text="Google" url="http://www.google.com"/>
    </div>
);