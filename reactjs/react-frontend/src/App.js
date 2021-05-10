import './App.css';
import TravellerComponent from './components/TravellerComponent';
import TravellerBestCities from './components/TravellerBestCities';

function App() {
  return (
    <div className="App">
      {/* <input type="text" id="TravellerName" value=""></input> */}
      <TravellerComponent />
      <TravellerBestCities />
    </div>
  );
}

export default App;
