import axios from "axios";

const Travellers_url = 'http://localhost:8080/travellers';

class TravellerService
{
    getTravellers()
    {
        return axios.get(Travellers_url);
    }

    findBestCitiesForTheUser()
    {
        //serch box name =>
        return axios.get("http://localhost:8080/Nick/bestCity")
    }
}

export default new TravellerService()
