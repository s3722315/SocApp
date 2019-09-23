import axios from 'axios'
import { API_URL, JPA_API_URL } from '../../Constants'

class SignupDataService {
    createNewAccount(account) {
        return axios.put(`${JPA_API_URL}/blah`, account)
    }
}

export default new SignupDataService()