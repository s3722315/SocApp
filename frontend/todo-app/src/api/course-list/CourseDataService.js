import axios from 'axios'
import { API_URL, JPA_API_URL } from '../../Constants'

class CourseDataService {

    retrieveAllCourses(name) {
        //console.log('executed service')
        return axios.get(`${JPA_API_URL}/${name}/courses`);
    }

    retrieveMyCourses(name) {
        //console.log('executed service')
        return axios.get(`${JPA_API_URL}/users/${name}/courses`);
    }

    retrieveACoursesEnrollStatus(id, name) {
        //console.log('executed service')
        return axios.get(`${JPA_API_URL}/users/${name}/${id}/enroll/status`);
    }

    retrieveACourse(id) {
        //console.log('executed service')
        return axios.get(`${JPA_API_URL}/courses/${id}`);
    }

    enrolACourse(id, name) {

        return axios.put(`${JPA_API_URL}/courses/${id}/enroll`, name)
    }

    unenrolACourse(id, name) {

        return axios.put(`${JPA_API_URL}/courses/${id}/drop`, name)
    }

}

export default new CourseDataService()