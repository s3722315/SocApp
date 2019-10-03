import React, { Component } from 'react'
import CourseDataService from '../../api/course-list/CourseDataService.js'
import AuthenticationService from '../todo/AuthenticationService.js'

class AllCoursesComponent extends Component {

    constructor(props) {
        console.log('constructor')
        super(props)
        this.state = {
            courseList: [],
            myCourseList: [],
            message: null
        }
        this.gotoCourse = this.gotoCourse.bind(this)
        this.enrollInCourse = this.enrollInCourse.bind(this)
        this.refreshCourses = this.refreshCourses.bind(this)
        this.refreshMyCourses = this.refreshMyCourses.bind(this)
    }

    componentWillUnmount() {
        console.log('componentWillUnmount')
    }

    shouldComponentUpdate(nextProps, nextState) {
        console.log('shouldComponentUpdate')
        console.log(nextProps)
        console.log(nextState)
        return true
    }

    componentDidMount() {
        console.log('componentDidMount')
        this.refreshCourses()
        this.refreshMyCourses()
        console.log(this.state)
    }

    gotoCourse(id) {
        console.log('move to course' + id)
        this.props.history.push(`/courses/${id}`)

    }

    enrollInCourse(id, course) {
        console.log('enrolled in ' + id)
        CourseDataService.enrolACourse(id, course)
        .then(
            response => {
                this.refreshMyCourses()
                this.refreshCourses()
                this.setState({ message: "The course " + course.code + ": " + course.coursename + " has been enrolled" })
            }
        )
    }

    unenrollCourse(id, course)
    {
        console.log('enrolled in ' + id)
        CourseDataService.unenrolACourse(id, course)
        .then(
            response => {
                this.refreshMyCourses()
                this.refreshCourses()
                this.setState({ message: "The course " + course.code + ": " + course.coursename + " has been unenrolled" })
            }
        )
    }

    refreshCourses() {
        console.log('refresh Courses')
        CourseDataService.retrieveAllCourses()
        .then(
            response => {
                //console.log(response);
                this.setState({ courseList: response.data })
                
            }
        )
        
    }
    
    refreshMyCourses() {
        console.log('refresh My Courses')
        let username = AuthenticationService.getLoggedInUserName()
        CourseDataService.retrieveMyCourses(username)
        .then(
            response => {
                this.setState({myCourseList: response.data})
            }
        )
    }

    actionButton(course) {
        if (course.status === "available") {
            return <td><button className="btn btn-success" id={course.id} onClick={() => this.enrollInCourse(course.id, course)}>Enroll</button></td>;
        }

        if (course.status === "enrolled") {
            return <td><button className="btn btn-warning" id={course.id} onClick={() => this.unenrollCourse(course.id, course)}>Unenroll</button></td>;
        }

        return <td>None</td>;
    }

    render() {

        console.log('render')
        return(
            <div>
                <div className="container">
                <h1>My Courses</h1>
                {this.state.message && <div class="alert alert-success">{this.state.message}</div>}
                <table className="table">
                    <thead>
                        <tr>
                            <th>Code</th>
                            <th>Name</th>
                            <th>Status</th>
                            <th>Link</th>
                            <th>Action</th>
                        </tr>
                    </thead>

                    <tbody>
                        {

                            this.state.myCourseList.map(
                                course =>
                                    <tr key={course.id}>
                                        <td>{course.code}</td>
                                        <td>{course.coursename}</td>
                                        <td>{course.status}</td>
                                        <td><button className="btn btn-success" onClick={() => this.gotoCourse(course.id)}>Go To</button></td>
                                        {this.actionButton(course)}
                                    </tr>
                            )
                        }
                    </tbody>
                </table>

                    <hr
                        style={{
                            color: "grey",
                            backgroundColor: "grey",
                            height: 3
                        }}
                    />

                    <h1>All Courses</h1>
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Code</th>
                                <th>Name</th>
                                <th>Status</th>
                                <th>Link</th>
                                <th>Action</th>
                            </tr>
                        </thead>

                        <tbody>
                            {

                                this.state.courseList.map(
                                    course =>
                                        <tr key={course.id}>
                                            <td>{course.code}</td>
                                            <td>{course.coursename}</td>
                                            <td>{course.status}</td>
                                            <td><button className="btn btn-success" onClick={() => this.gotoCourse(course.id)}>Go To</button></td> 
                                            {this.actionButton(course)}
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>

        )
    }
}

export default AllCoursesComponent  