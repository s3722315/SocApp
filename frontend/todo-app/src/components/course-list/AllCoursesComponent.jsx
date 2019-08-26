import React, { Component } from 'react'
import CourseDataService from '../../api/course-list/CourseDataService.js'

class AllCoursesComponent extends Component {

    constructor(props) {
        super(props)

        this.courseList = []

    }

    gotoCourse(id) {
        console.log('move to course' + id)
        this.props.history.push(`/courses/${id}`)

    }

    render() {
        CourseDataService.retrieveAllCourses()
        .then(
            response => {
                //console.log(response);
                this.setCourseList({ courseList: response.data })
            }
        )


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

                                this.courseList.map(
                                    course =>
                                        <tr key={course.id} onClick={this.props.history.push(`/courses/${course.id}`)}>
                                            <td>{course.id}</td>
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