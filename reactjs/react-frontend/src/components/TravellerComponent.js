import React from "react";
import TravellerService from "../services/TravellerService";

class TravellerComponent extends React.Component
{
    constructor()
    {
        super()
        this.state =
        {
            travellers:[]
        }
    }

    componentDidMount()
    {
        TravellerService.getTravellers().then((response)=>
        {
            this.setState({travellers: response.data})
        });
    }

    render()
    {
        return (
            <div>
                <h1 className= "text-center">Travellers list</h1>
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <td>Traveller's name </td>
                            <td>Traveller's city </td>
                            <td>Traveller's region</td>
                            <td>Traveller's timestamp</td>
                            <td>Traveller's Visit City </td>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.travellers.map(
                                traveller =>
                                <tr key= {traveller.name}>
                                    <td> {traveller.name}</td>
                                    <td> {traveller.cityName}</td>
                                    <td>{traveller.country}</td>
                                    <td>{traveller.timeStamp}</td>
                                    <td> {traveller.visit.cityName}</td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        )
    }
}
export default TravellerComponent

