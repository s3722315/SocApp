import React, { Component } from 'react'
import SignupDataService from '../../api/signup/SignupDataService.js'

class SignupComponent extends Component {

    constructor(props) {
        super(props)
        
        this.state = {
            username: '',
            password: '',
            failSignup: false
        }
        this.handleChange = this.handleChange.bind(this)
        this.signupClicked = this.signupClicked.bind(this)
    }

    handleChange(event) {
        //console.log(this.state);
        this.setState(
            {
                [event.target.name]
                    : event.target.value
            }
        )
    }

    signupClicked() {
        console.log("trying to singup, clicked")
        console.log(this.state)
        SignupDataService.createNewAccount(this.state.username, this.state.password)
        .then(
            response => {
            this.props.history.push(`/login`)
        }).catch(() => {
            this.setState({failSignup: true })
        })
    }

    render() {
        return (
            <div>
                <h1>Sign Up</h1>
                <div className="container">
                    {this.state.failSignup && <div className="alert alert-warning">Username already taken</div>}
                    User Name: <input type="text" name="username" value={this.state.username} onChange={this.handleChange} />
                    Password: <input type="password" name="password" value={this.state.password} onChange={this.handleChange} />
                    <button className="btn btn-success" onClick={() => this.signupClicked()}>Sign Up</button>
                </div>
            </div>
        )
    }
}

export default SignupComponent