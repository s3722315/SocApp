import axios from 'axios'
import { JPA_API_URL } from '../../Constants'

class CourseDataService {

    // used for getting all courses list
    retrieveAllCourses(name) {
        //console.log('executed service')
        return axios.get(`${JPA_API_URL}/${name}/courses`);
    }

    // used for getting my courses list
    retrieveMyCourses(name) {
        //console.log('executed service')
        return axios.get(`${JPA_API_URL}/users/${name}/courses`);
    }

    // used for getting enroll status (not used anymore)
    // retrieveACoursesEnrollStatus(id, name) {
    //     //console.log('executed service')
    //     return axios.get(`${JPA_API_URL}/users/${name}/${id}/enroll/status`);
    // }

    // used to retrieve a specific course
    retrieveACourse(id) {
        //console.log('executed service')
        return axios.get(`${JPA_API_URL}/courses/${id}`);
    }

    // used to create a new enrolment relation
    enrolACourse(id, name) {

        return axios.put(`${JPA_API_URL}/users/${name}/courses/${id}/enroll`)
    }

    // used to delete a existing enrolment relation
    unenrolACourse(id, name) {

        return axios.put(`${JPA_API_URL}/users/${name}/courses/${id}/drop`, name)
    }

}

export default new CourseDataService()