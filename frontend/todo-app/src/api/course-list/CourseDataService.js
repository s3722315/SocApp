import axios from 'axios'
import { API_URL, JPA_API_URL } from '../../Constants'

class CourseDataService {

    retrieveAllCourses(name) {
        //console.log('executed service')
        return axios.get(`${API_URL}/users/${name}/courses`);
    }

    retrieveMyCourses(name) {
        //console.log('executed service')
        return axios.get(`${JPA_API_URL}/users/${name}/mycourses`);
    }

    retrieveACourse(id) {
        //console.log('executed service')
        return axios.get(`${JPA_API_URL}/courses/${id}`);
    }

}

export default new CourseDataService()