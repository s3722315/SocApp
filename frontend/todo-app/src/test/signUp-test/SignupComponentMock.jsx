import React, { Component } from 'react'

class SignupComponentMock extends Component {

    constructor(props) {
        super(props)
        
        this.state = {
            username: '',
            password: '',
            failSignup: false,
            existingUser: [
                {
                    username: 'Sam',
                    password: 'poop'
                },
                {
                    username: 'Mat',
                    password: 'fat'
                }
            ]
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
        if (this.state.username.length > 0 && this.state.username.length <= 20 && this.state.password.length > 0 && this.state.password.length <= 20 ) {
            this.setState(
                { failSignup: true }
            )
        }

        this.state.existingUser.map(
            user =>
            {
                if (this.state.username == user.username) {
                    this.setState({ failSignup: true })
                }
            }
        )
    }

    render() {
        return (
            <div>
                <h1>Sign Up</h1>
                <div className="container">
                    {this.state.failSignup && <div className="alert alert-warning">Failed signup<br></br>
                    Username may be already taken<br></br>
                    Make sure username and password is 20 characters</div>}
                    User Name: <input type="text" name="username" id="user" value={this.state.username} onChange={this.handleChange} />
                    Password: <input type="password" name="password" id="pass" value={this.state.password} onChange={this.handleChange} />
                    <button className="btn btn-success" id="enter" onClick={() => this.signupClicked()}>Sign Up</button>
                </div>
            </div>
        )
    }
}

export default SignupComponentMock