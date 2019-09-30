import axios from 'axios'
import { API_URL, JPA_API_URL } from '../../Constants'

class SignupDataService {
    createNewAccount(username, password) {
<<<<<<< HEAD
        return axios.post(`${JPA_API_URL}/new-account`,{
=======
        return axios.post(`${JPA_API_URL}/new-account`, {
>>>>>>> 5d7d546b31c4cba3f4e0c657b255b92ed6eba351
        username, 
        password
        })
    }
}

export default new SignupDataService()