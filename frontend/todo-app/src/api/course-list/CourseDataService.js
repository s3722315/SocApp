import axios from 'axios'
import { API_URL, JPA_API_URL } from '../../Constants'

class CourseDataService {

    retrieveAllCourses() {
        //console.log('executed service')
        return axios.get(`${JPA_API_URL}/courses`);
    }

    retrieveMyCourses(name) {
        //console.log('executed service')
        return axios.get(`${JPA_API_URL}/users/${name}/mycourses`);
    }

    retrieveACourse(name, id) {
        //console.log('executed service')
        return axios.get(`${JPA_API_URL}/users/${name}courses/${id}`);
    }

}

export default new CourseDataService()