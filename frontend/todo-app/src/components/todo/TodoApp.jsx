import React, {Component} from 'react'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import AuthenticatedRoute from './AuthenticatedRoute.jsx'
import LoginComponent from './LoginComponent.jsx'
import ListTodosComponent from './ListTodosComponent.jsx'
import ErrorComponent from './ErrorComponent.jsx'
import HeaderComponent from './HeaderComponent.jsx'
import FooterComponent from './FooterComponent.jsx'
import LogoutComponent from './LogoutComponent.jsx'
import WelcomeComponent from './WelcomeComponent.jsx'
import TodoComponent from './TodoComponent.jsx'

import SignupComponent from '../signup/SignupComponent.jsx'

import AllCoursesComponent from '../course-list/AllCoursesComponent.jsx'
import CourseComponent from '../course-list/CourseComponent.jsx'

import StudentComponent from '../student-list/StudentComponent.jsx'
import AllStudentsComponent from '../student-list/AllStudentsComponent.jsx'

class TodoApp extends Component {
    render() {
        return (
            <div className="TodoApp">
                <Router>
                    <>
                        <HeaderComponent/>
                        <Switch>
                            <Route path="/" exact component={LoginComponent}/>
                            <Route path="/login" component={LoginComponent}/>
                            <AuthenticatedRoute path="/welcome/:name" component={WelcomeComponent}/>
                            <AuthenticatedRoute path="/todos/:id" component={TodoComponent}/>
                            <AuthenticatedRoute path="/todos" component={ListTodosComponent}/>
                            <AuthenticatedRoute path="/logout" component={LogoutComponent}/>

                            <Route path="/signup" component={SignupComponent}/>

                            <AuthenticatedRoute path="/courses/:id" component={CourseComponent}/>
                            <AuthenticatedRoute path="/courses" component={AllCoursesComponent}/>

                            <AuthenticatedRoute path="/students/:id" component={StudentComponent}/>
                            <AuthenticatedRoute path="/students" component={AllStudentsComponent}/>

                            <Route component={ErrorComponent}/>
                        </Switch>
                        <FooterComponent/>
                    </>
                </Router>
                {/*<LoginComponent/>
                <WelcomeComponent/>*/}
            </div>
        )
    }
}

export default TodoApp
