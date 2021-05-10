import axios from "axios";

const Travellers_url = 'http://localhost:8080/travellers';

class TravellerService
{
    getTravellers()
    {
        return axios.get(Travellers_url);
    }

}

export default new TravellerService()
