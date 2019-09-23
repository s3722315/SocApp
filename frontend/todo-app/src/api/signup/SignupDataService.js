import axios from 'axios'
import { API_URL, JPA_API_URL } from '../../Constants'

class SignupDataService {
    createNewAccount() {
        return axios.put(`${JPA_API_URL}/blah)
    }
}

export default new SignupDataService()