import React, { Component } from 'react'
import CourseDataService from '../../api/course-list/CourseDataService.js'

class CourseComponent extends Component {

    constructor(props) {
        super(props)

        this.state={
            id: this.props.match.params.id,
            course: null
        }
        
        this.refreshCourse = this.refreshCourse.bind(this)
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
        this.refreshCourse()
        console.log(this.state)
    }

    refreshCourse()
    {
        console.log('getting course')
        CourseDataService.retrieveACourse(this.state.id)
        .then( 
            response => {
                this.setState({ course: response.data })
            }
        )
    }

    enrollInCourse() {
        console.log('enrolled in ' + this.state.id)
        CourseDataService.enrolACourse(this.state.id, this.state.course)
        .then(
            response => {
                this.refreshCourses()
            }
        )
    }

    unenrollCourse()
    {
        console.log('enrolled in ' + this.state.id)
        CourseDataService.unenrolACourse(this.state.id, this.state.course)
        .then(
            response => {
                this.refreshCourse()
            }
        )
    }

    render() {
        
        return(
            <div>
                {/* <h1>{this.state.course.code}:{this.state.course.coursename}</h1> */}
                <div>
                    This is where the course information goes
                </div>

                <div className="container">
                    <h2>Study Groups</h2>
                    <table className="table">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Link</th>
                            </tr>
                        </thead>

                        <tbody>
                            <tr>
                                <td>1</td>
                                <td>Study Group 1</td>
                                <td><button className="btn btn-success" >Go To</button></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        )
    }

}
export default CourseComponent