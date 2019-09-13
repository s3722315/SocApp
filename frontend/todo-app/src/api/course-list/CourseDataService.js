import axios from 'axios'
import { API_URL, JPA_API_URL } from '../../Constants'

class CourseDataService {

    retrieveAllCourses() {
        //console.log('executed service')
        return axios.get(`${JPA_API_URL}/courses`);
    }

    retrieveMyCourses(name) {
        //console.log('executed service')
        return axios.get(`${JPA_API_URL}/users/${name}/courses`);
    }

    retrieveACourse(name, id) {
        //console.log('executed service')
        return axios.get(`${JPA_API_URL}/users/${name}courses/${id}`);
    }

    enrolACourse(id, course) {

        return axios.put(`${JPA_API_URL}/courses/${id}/enroll`, course)
    }

    unenrolACourse(id, course) {

        return axios.put(`${JPA_API_URL}/courses/${id}/drop`, course)
    }

}

export default new CourseDataService()