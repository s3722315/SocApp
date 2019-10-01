import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import StudentDataService from '../../api/student-list/StudentDataService.js'

class StudentComponent extends Component {

    constructor(props) {
        super(props)

        this.state={
            id: this.props.match.params.id,
            student: []
        }

        this.refreshStudent = this.refreshStudent.bind(this)
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
        this.refreshStudent()
        console.log(this.state)
    }

    refreshStudent()
    {
        console.log('getting student')
        StudentDataService.retrieveAStudent(this.state.id)
        .then(
            response => {
                this.setState({ student: response.data })
            }
        )
    }

    enrollInStudent() {
        console.log('enrolled in ' + this.state.id)
        StudentDataService.enrolAStudent(this.state.id, this.state.student)
        .then(
            response => {
                this.refreshStudents()
            }
        )
    }

    unenrollStudent()
    {
        console.log('enrolled in ' + this.state.id)
        StudentDataService.unenrolAStudent(this.state.id, this.state.student)
        .then(
            response => {
                this.refreshStudent()
            }
        )
    }

    render() {

        return(
            <div>
                <h1>{this.state.student.code}:  {this.state.student.studentname}</h1>
                <div className="container">
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                        <ul className="navbar-nav">
                            <li><Link className="nav-link" to={() => this.props.history.push(`/students/${this.id}`)}>Details</Link></li>
                            <li><Link className="nav-link" to={() => this.props.history.push(`/students/${this.id}`)}>Study Groups</Link></li>
                        </ul>
                    </nav>

                    <div style={{textAlign: 'left', margin: '10px 15px'}}>
                        <h2>Details:</h2>
                        {this.state.student.details}
                    </div>
                </div>
            </div>
        )
    }

}
export default StudentComponent
