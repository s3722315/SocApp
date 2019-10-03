import axios from 'axios'
import { JPA_API_URL } from '../../Constants'

class StudentDataService {

    retrieveAllStudents() {
        //console.log('executed service')
        return axios.get(`${JPA_API_URL}/students`);
    }

    retrieveMyStudents(name) {
        //console.log('executed service')
        return axios.get(`${JPA_API_URL}/users/${name}/students`);
    }

    retrieveAStudent(id) {
        //console.log('executed service')
        return axios.get(`${JPA_API_URL}/students/${id}`);
    }

    enrolAStudent(id, student) {

        return axios.put(`${JPA_API_URL}/students/${id}/enroll`, student)
    }

    unenrolAStudent(id, student) {

        return axios.put(`${JPA_API_URL}/students/${id}/drop`, student)
    }

}

export default new StudentDataService()
