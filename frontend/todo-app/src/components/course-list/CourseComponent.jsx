import React, { Component } from 'react'
import CourseDataService from '../../api/course-list/CourseDataService.js'

class CourseComponent extends Component {

    constructor(props) {
        super(props)

        this.state={
            id: this.props.match.params.id,
            course: null
        }
        
    }

    render() {
        CourseDataService.retrieveACourse(this.id)
        .then( 
            response => {
                this.setState({ course: response.data })
            }
        )

        return(
            <div>
                <h1>{this.state.id} This is my fav course</h1>
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