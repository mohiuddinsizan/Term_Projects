import React from 'react'
import './UserLogin.css'
const UserLogin = () => {
    return ( 
        <div className='userlogin'>
            <div className='userlogin-container'>
                <h1>Login</h1>
                <div className='login-fields'>
                    <input type = 'text1' placeholder='Username'></input>
                    <input type = 'password' placeholder='password'></input>
                </div>
                <button>Continue</button>
            </div>
        </div>
     );
}
 
export default UserLogin;