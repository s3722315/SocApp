import axios from 'axios'
import { JPA_API_URL } from '../../Constants'

class SignupDataService {
    createNewAccount(username, password) {
        return axios.post(`${JPA_API_URL}/new-account`, {
        username, 
        password
        })
    }
}

export default new SignupDataService()