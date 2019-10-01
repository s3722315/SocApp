import React, { Component } from 'react'
import StudentDataService from '../../api/student-list/StudentDataService.js'
import AuthenticationService from '../todo/AuthenticationService.js'

class AllStudentsComponent extends Component {

    constructor(props) {
        console.log('constructor')
        super(props)
        this.state = {
            studentList: [],
            myStudentList: [],
            message: null
        }
        this.gotoStudent = this.gotoStudent.bind(this)
        this.enrollInStudent = this.enrollInStudent.bind(this)
        this.refreshStudents = this.refreshStudents.bind(this)
        this.refreshMyStudents = this.refreshMyStudents.bind(this)
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
        this.refreshStudents()
        this.refreshMyStudents()
        console.log(this.state)
    }

    gotoStudent(id) {
        console.log('move to student' + id)
        this.props.history.push(`/students/${id}`)

    }

    enrollInStudent(id, student) {
        console.log('enrolled in ' + id)
        StudentDataService.enrolAStudent(id, student)
        .then(
            response => {
                this.refreshMyStudents()
                this.refreshStudents()
                this.state.message = "The student " + student.code + ": " + student.studentname + " has been enrolled"
            }
        )
    }

    unenrollStudent(id, student)
    {
        console.log('enrolled in ' + id)
        StudentDataService.unenrolAStudent(id, student)
        .then(
            response => {
                this.refreshMyStudents()
                this.refreshStudents()
                this.state.message = "The student " + student.code + ": " + student.studentname + " has been unenrolled"
            }
        )
    }

    refreshStudents() {
        console.log('refresh Students')
        StudentDataService.retrieveAllStudents()
        .then(
            response => {
                //console.log(response);
                this.setState({ studentList: response.data })

            }
        )

    }

    refreshMyStudents() {
        console.log('refresh My Students')
        let username = AuthenticationService.getLoggedInUserName()
        StudentDataService.retrieveMyStudents(username)
        .then(
            response => {
                this.setState({myStudentList: response.data})
            }
        )
    }

    actionButton(student) {
        if (student.status == "available") {
            return <td><button className="btn btn-success" onClick={() => this.enrollInStudent(student.id, student)}>Enroll</button></td>;
        }

        if (student.status == "enrolled") {
            return <td><button className="btn btn-warning" onClick={() => this.unenrollStudent(student.id, student)}>Unenroll</button></td>;
        }

        return <td>None</td>;
    }

    render() {

        console.log('render')
        return(
            <div>
                <div className="container">
                <h1>My Students</h1>
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

                            this.state.myStudentList.map(
                                student =>
                                    <tr key={student.id}>
                                        <td>{student.code}</td>
                                        <td>{student.studentname}</td>
                                        <td>{student.status}</td>
                                        <td><button className="btn btn-success" onClick={() => this.gotoStudent(student.id)}>Go To</button></td>
                                        {this.actionButton(student)}
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

                    <h1>All Students</h1>
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

                                this.state.studentList.map(
                                    student =>
                                        <tr key={student.id}>
                                            <td>{student.code}</td>
                                            <td>{student.studentname}</td>
                                            <td>{student.status}</td>
                                            <td><button className="btn btn-success" onClick={() => this.gotoStudent(student.id)}>Go To</button></td>
                                            {this.actionButton(student)}
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

export default AllStudentsComponent
