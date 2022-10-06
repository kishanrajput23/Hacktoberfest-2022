 # As goal of these series is to go through as many aspect of web development as possible, play with new tech and learn something new and interesting. And we will do this by creating a text-based browser game.
![image](https://user-images.githubusercontent.com/98735376/194227825-7f4f5545-2d7b-4897-913e-38092b55ef61.png)

-If you haven’t read previous posts — here they are:

-[Iteration 0] Idea: https://medium.com/@dmitrykmita/how-to-build-browser-game-iteration-0-idea-c0865d125148
-[Iteration 1] Domains: https://medium.com/@dmitrykmita/how-to-build-browser-game-iteration-1-domains-a62fbd9d9fba
-Now it’s time for GUI. I am terrible at drawing, also, I’ve never been a huge gamer, so it was actually a challenge to think of GUI for this game. I went through some games, googled a lot, checked some resources on game dev and made a wireframe using great tool https://wireframe.cc/


# Generic GUI
-Then I used another great tool https://coolors.co/ for colour scheme.

Now the ReactJS part. This will be a basic tutorial on how to build a UI on ReactJS.

I am using webpack for bundling and babel for compiling JS.

So to create your front-end with react you can either use react-create-app service or just initialise npm project in your public folder (./web in case of Symfony framework).

After that add few dependencies:

npm install --save babel-core babel-loader babel-preset-es2015 babel-preset-react react react-dom webpack
Now we have webpack for bundling, babel for compiling and react for coding installed.

-Next thing is to specify which JS presets you will be using, so create .babelrc file and add the ones you will use (in my case its es2015 and react jsx):

{
  "presets": [
    "react",
    "es2015"
  ]
}
-Great, now babel knows how to compile our application.

It’s time to create the actual ui code, so I created another folder (I called it library, don’t know why, you can call it src) and added a new file called Game.js

Game.js will be my entry point of the front-end.

To make the webpack work I have created webpack.config.js file in npm root folder and added some general code:

const path = require('path');

-module.exports = {
    entry: './libraries/Game.js',
    output: {
        path: path.join(__dirname, 'dist'),
        filename: 'game.js'
    },
    module: {
        loaders: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                loader: 'babel-loader'
            }
        ]
    }
};
![image](https://user-images.githubusercontent.com/98735376/194227898-181773d1-292d-4a27-bf24-69b73674fa95.png)

- I am using NodeJS path library to make the path of entry file easier. The code inside of this module is pretty straight forward, you set the entry file, set the output file and how to load the modules.

I hate running webpack through node_modules folder, so I add this script to package.json:

"scripts": {
  "test": "echo \"Error: no test specified\" && exit 1",
  "dev": "webpack -wd"
},
This will run webpack in dev mode and start watching for any changes, so now I can simply run:

npm run webpack
And it should compile your bundle. Oh, but its empty, because our Game.js is empty. Lets create our new Component!

As you know ReactJS is based on Components, which inherit, collaborate and just live together very friendly.

To create a Component you need to use this syntax (there are few ways, but this is called the most correct one):

import React from "react";
import ReactDOM from "react-dom";

 - class Game extends React.Component {

    render() {
        return (
            <div>
                <div className="container-fluid">
                    <div className="row">
                        <div className="col-md-12">
                            <CharacterWidget />
                            <HeaderWidget />
                        </div>
                    </div>
                </div>
                <div className="container-fluid">
                    <div className="row">
                        <div className="col-md-6 text-center">
                            <Map />
                        </div>
                        <div className="col-md-6">
                            <MapDetails />
                        </div>
                    </div>
                </div>
                <Inventory />
            </div>
        )
    }
};

var mountNode = document.getElementById("game");
if (mountNode) {
    ReactDOM.render(<Game />, mountNode);
}
- React Component requires only 1 method called render() which returns the template of this specific component. As you can see I have created some dumb template using bootstrap. Because we are using JSX, instead of class we have to use className. But what are these <CharacterWidget /> <HeaderWidget /> <Map /> <MapDetails />? Well these are other components we will create. For now I have created all those components and all they have as template:

<div className=”c-character-widget”>Character Widget</div>
<div className=”c-header-widget”>Header Widget</div>
<div className=”c-map”>Map</div>
<div className=”c-map-details”>Map Details</div>
As I mentioned c-% class, lets talk about css a little bit.

- I am using SASS for this application and there are 2 ways of bundling our css. Either I create all scss files separately for each component, add scss file to every component as dependency and then just add sass-compiler to webpack (which is the best solution for websites, because then webpack will generate the styles as needed for each page depending on components page using), or create main.scss where you will add all component scss files and create 1 bundle css (more preferable for my case, because the game does not have many pages).

I am using “kind of” BEM methodology to define my css classes. Here is an example of block with extras specific item will provide:

.c-extra {
  margin-top: 10px;
  .c-extra--title {
    font-size: 13px;
    color: $color-gold-primary;
    text-shadow: 0 0 5px #000;
  }

  .c-extra--item {
    font-size: 12px;
    color: $color-gray-light-primary;

    &.c-extra--item__required {
      color: $color-red-primary;
    }

    .c-extra--item-stat {
      display: inline-block;
      margin-right: 5px;
    }
    .c-extra--item-value {
      display: inline-block;
    }
  }
}
- And here is jsx template:

<div className="c-extra">
    <div className="c-extra--title">Extras</div>
    {this.props.item.extras.map((item, i) => {
        return (
            <div key={i} className="c-extra--item">
                <div className="c-extra--item-stat">
                  {item.attribute}
                </div>
                <div className="c-extra--item-value">
                  {item.value}
                </div>
            </div>
        )
    })}
</div>
- As you can see even though these classes become a little bit long — it is very easy to understand the structure of component + its reusable and if you move it to any other place of the application — it should stay the same. Idea of component is to be stateless, no matter where you include it — it should look the same.

This is an example of very ugly, but working character widget:


I have not touched data for now, because all we need atm — is just to build the UI, and then we will move forward.

I think this is enough for now, if you have any questions regarding ReactJS Components, sass, webpack or anything else covered in this article — feel free to ask in comment section bellow.

What I can tell — I changed my attitude to ReactJS, because found it very slim and powerful tool to build Component Based UIs and use all the power of VirtualDOM.
