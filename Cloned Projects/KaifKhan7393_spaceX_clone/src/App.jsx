import React from 'react';
import Header from './components/Header';
import Banner from './components/Banner';
import Footer from './components/Footer';
const App = () => {
  return (
    <div>
      <Header />
      <Banner bgImage={"https://cdn.pixabay.com/photo/2015/03/26/18/36/rocket-launch-693206_960_720.jpg"} title={"RECENT LAUNCH"} heading={"STARLINK MISSION"} btnText={"Rewatch"} />
      <Banner bgImage={"https://cdn.pixabay.com/photo/2020/05/31/09/15/starship-5241913_960_720.jpg"} heading={"SPACEX + T-MOBILE UPDATE"} btnText={"Learn More"} />
      <Banner bgImage={"https://cdn.pixabay.com/photo/2015/03/26/18/36/spacecraft-693218_960_720.jpg"} heading={"STARSHIP UPDATE"} btnText={"Learn More"} />
      <Banner bgImage={"https://cdn.pixabay.com/photo/2015/03/26/18/36/spacex-693229_960_720.jpg"} heading={"STARSHIP TO LAND NASA ASTRONAUTS ON THE MOON"} btnText={"Learn More"} />
      <Footer />
    </div>
  )
}

export default App