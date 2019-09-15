import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import CourseDataService from '../../api/course-list/CourseDataService.js'

class CourseComponent extends Component {

    constructor(props) {
        super(props)

        this.state={
            id: this.props.match.params.id,
            course: []
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
                <h1>{this.state.course.code}:  {this.state.course.coursename}</h1>
                <div className="container">
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                        <ul className="navbar-nav">
                            <li><Link className="nav-link" to={() => this.props.history.push(`/courses/${this.id}`)}>Details</Link></li>
                            <li><Link className="nav-link" to={() => this.props.history.push(`/courses/${this.id}`)}>Study Groups</Link></li>
                        </ul>
                    </nav>

                    <div style={{textAlign: 'left', margin: '10px 15px'}}>
                        <h2>Details:</h2>
                        {this.state.course.details}
                    </div>
                </div>
            </div>
        )
    }

}
export default CourseComponent