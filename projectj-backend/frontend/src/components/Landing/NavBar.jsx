import React from 'react';
import { Navbar,NavItem,NavbarBrand,Container } from 'react-bootstrap';

const NavBar = () => {
  return (
    <div className='navbar'>
        <Navbar expand="lg" className="myNavColor" >
            <Container>
                <Navbar.Brand href="/">
                    <img
                        src="../../assets/logo.png"
                        className='logo-image'
                        alt='logo'
                    />
                </Navbar.Brand>



            </Container>
        </Navbar>
    </div>
  )
}

export default NavBar
