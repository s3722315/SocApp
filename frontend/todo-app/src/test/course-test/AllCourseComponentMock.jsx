import React, { Component } from 'react'

class AllCoursesComponentMock extends Component {

    constructor(props) {
        console.log('constructor')
        super(props)
        this.state = {
            courseList: [
                {
                    id: 1,
                    code: "bruh1",
                    coursename: "bruh science",
                    status: "available"
                },
                {
                    id: 2,
                    code: "bruh2",
                    coursename: "bruh physics",
                    status: "unavailable"
                },
                {
                    id: 3,
                    code: "bruh3",
                    coursename: "bruh bro",
                    status: "full"
                },
                {
                    id: 4,
                    code: "bruh4",
                    coursename: "bruh chad",
                    status: "enrolled"
                }
            ],
            myCourseList: [
                {
                    id: 4,
                    code: "bruh4",
                    coursename: "bruh chad",
                    status: "enrolled"
                }
            ],
            message: null
        }

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

    enrollInCourse(id) {
        console.log('enrolled in ' + id)
        this.state.courseList.map(
            course =>
            {if (course.id == id) {
                course.status = "enrolled"
                this.state.message = "The course " + course.code + ": " + course.coursename + " has been unenrolled"
            }}
        )
        this.refreshMyCourses()
    }

    unenrollCourse(id)
    {
        console.log('enrolled in ' + id)

        this.state.courseList.map(
            course =>
            {if (course.id == id) {
                course.status = "available"
                this.state.message = "The course " + course.code + ": " + course.coursename + " has been unenrolled"
            }}
        )
        this.refreshMyCourses()
    }

    refreshCourses() {
        console.log('refresh Courses(does nothing)')
    }
    
    refreshMyCourses() {
        console.log('refresh My Courses')
        this.state.myCourseList = []

        this.state.courseList.map(
            course =>
            {if (course.status == "enrolled") {
                this.state.myCourseList.push(course)
            }}
        )
        console.log(this.state)
    }

    actionButton(course, listType) {
        if (listType == "all") {
            if (course.status == "available") {
                return <td><button className="btn btn-success" id={`all-enroll-${course.id}`} onClick={() => this.enrollInCourse(course.id)}>Enroll</button></td>;
            }
    
            if (course.status == "enrolled") {
                return <td><button className="btn btn-warning" id={`all-unenroll-${course.id}`} onClick={() => this.unenrollCourse(course.id)}>Unenroll</button></td>;
            }
        }

        if (listType == "my") {
            if (course.status == "available") {
                return <td><button className="btn btn-success" id={`my-enroll-${course.id}`} onClick={() => this.enrollInCourse(course.id)}>Enroll</button></td>;
            }
    
            if (course.status == "enrolled") {
                return <td><button className="btn btn-warning" id={`my-unenroll-${course.id}`} onClick={() => this.unenrollCourse(course.id)}>Unenroll</button></td>;
            }
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
                                        <td><button className="btn btn-success">Go To</button></td>
                                        {this.actionButton(course, "my")}
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
                                            <td><button className="btn btn-success">Go To</button></td> 
                                            {this.actionButton(course, "all")}
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

export default AllCoursesComponentMock  