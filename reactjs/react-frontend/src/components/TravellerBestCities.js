import React from "react";
import TravellerService from "../services/TravellerService";

class TravellerBestCities extends React.Component {
    constructor() {
        super()
        this.state =
        {
            BestCities: [],
        }
    }

    componentDidMount() {
        TravellerService.findBestCitiesForTheUser().then((response) => {
            this.setState({ BestCities: response.data})

        });
    }



    render() {
        return (
            <div>
                <p>this is the list of cities sorted for Nick</p>
                {

                    this.state.BestCities.map(
                        BestCity =>
                            <tr>
                                <td>{BestCity.cityName}</td>
                            </tr>
                    )                  

                }
            </div>
        )
    }
}
export default TravellerBestCities;
