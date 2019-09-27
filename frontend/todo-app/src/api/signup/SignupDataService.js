import axios from 'axios'
import { API_URL, JPA_API_URL } from '../../Constants'

class SignupDataService {
    createNewAccount(username, password) {
        return axios.put(`${JPA_API_URL}/new-account`,{
        username, 
        password
        })
    }
}

export default new SignupDataService()