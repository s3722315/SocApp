import axios from 'axios'
import { JPA_API_URL } from '../../Constants'

class SignupDataService {
    createNewAccount(username, password) {
<<<<<<< HEAD
        return axios.post(`${JPA_API_URL}/new-account`,{
=======
        return axios.post(`${JPA_API_URL}/new-account`, {
>>>>>>> e68c8ccfee9b95c9728510d7449a44eb3bc8fac8
        username, 
        password
        })
    }
}

export default new SignupDataService()