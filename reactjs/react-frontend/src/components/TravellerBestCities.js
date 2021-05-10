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
                this is the list of cities sorted for Nick
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
