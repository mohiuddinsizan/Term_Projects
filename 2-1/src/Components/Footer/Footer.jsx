import React from 'react'
import './Footer.css'
import logo from '../Assets/logo.png'
import fb from '../Assets/fb.webp'
import insta from '../Assets/insta.webp'
import wa from '../Assets/whatsapp.png'

const Footer = () => {
    return ( 
        <div className='footer'>
            <div className='footer-logo'>
                <img src={logo} alt = "" width="50" height="50"></img>
                <p>BookSphere</p>
            </div>
            <ul className='footer-links'>
                <li>Headoffice</li>
                <li>About</li>
                <li>Contact</li>
            </ul>
            <div className='social-container'>
                <div className='social'>
                    <img src={fb} alt ="" width="50" height="50"/>
                </div>
                <div className='social'>
                    <img src={wa} alt ="" width="50" height="50"/>
                </div>
                <div className='social'>
                    <img src={insta} alt ="" width="50" height="50"/>
                </div>
            </div>
            <div className='footer-copyright'>
                <hr/>
                <p>Copyright @ 1978 - All Right Reserved</p>
            </div>
        </div>
     );
}
 
export default Footer;