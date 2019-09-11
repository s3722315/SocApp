import React, { Component } from 'react'
import CourseDataService from '../../api/course-list/CourseDataService.js'
import AuthenticationService from '../todo/AuthenticationService.js'

class AllCoursesComponent extends Component {

    constructor(props) {
        console.log('constructor')
        super(props)
        this.state = {
            courseList: [],
            message: null
        }
        this.gotoCourse = this.gotoCourse.bind(this)
        this.refreshCourses = this.refreshCourses.bind(this)
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
        this.refreshCourses();
        console.log(this.state)
    }

    gotoCourse(id) {
        console.log('move to course' + id)
        this.props.history.push(`/courses/${id}`)

    }

    refreshCourses() {
        console.log('refresh')
        let username = AuthenticationService.getLoggedInUserName()
        CourseDataService.retrieveAllCourses(username)
        .then(
            response => {
                //console.log(response);
                this.setState({ message: `Tried to read data`})
                this.setState({ courseList: response.data })
                
            }
        )
    }

    render() {
        // CourseDataService.retrieveAllCourses()
        // .then(
        //     response => {
        //         //console.log(response);
        //         this.setState({ courseList: response.data })
        //     }
        // )

        console.log('render')
        return(
            <div>
            
                <h1>All Courses</h1>
                <div className="container">
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Code</th>
                                <th>Name</th>
                                <th>Status</th>
                                <th>Link</th>
                            </tr>
                        </thead>

                        <tbody>
                                <tr>
                                        <td>Bruh1231</td>
                                        <td>This is my fav course</td>
                                        <td>Available</td>
                                        <td><button className="btn btn-success" onClick={() => this.gotoCourse('Bruh1231')}>Go To</button></td>

                                </tr>

                            {

                                this.state.courseList.map(
                                    course =>
                                        <tr key={course.id}>
                                            <td>{course.code}</td>
                                            <td>{course.name}</td>
                                            <td>{course.status}</td>
                                            <td><button className="btn btn-success" onClick={() => this.gotoCourse(course.id)}>Go To</button></td>
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