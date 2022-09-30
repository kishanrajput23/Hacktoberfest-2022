import React from 'react'
import styled from 'styled-components'

const FooterData = [
    {
        id: 2,
        text: 'Twitter'
    },
    {
        id: 3,
        text: 'Youtube'
    },
    {
        id: 4,
        text: 'Instagram'
    },
    {
        id: 5,
        text: 'Flicker'
    },
    {
        id: 6,
        text: 'LinkedIN'
    },
    {
        id: 7,
        text: 'Privacy Policy'
    },
    {
        id: 8,
        text: 'Suppliers'
    },
];
const Footer = () => {
    const year = new Date().getFullYear();
    return (
        <SpaceXFooter>
            <li className="active">SpaceX ©️ ${year}</li>
            <FooterMenu>
                {

                    FooterData.map((curEle) => {
                        return (
                            <li key={curEle.id}>{curEle.text}</li>
                        )
                    })
                }
            </FooterMenu>
        </SpaceXFooter>
    )
}

export default Footer

const SpaceXFooter = styled.div`
    width:100%;
    height:100px;
    background:#000;
    color:white;
    display:flex;
    justify-content:center;
    align-items:center;
    li{
        list-style:none;
        color:gray;
        font-size:12px;
        font-weight:normal;
    }
    @media (max-width: 840px) {
        flex-direction:column;
        height:130px;
        li{
            margin-bottom:15px;
        }
    }
`
const FooterMenu = styled.ul`
    display:flex;
    list-style-type:none;
    align-items:center;
    justify-content:center;
    flex-wrap:wrap;
    li{
        color:white;
        margin-left:30px;
        list-style:none;
        font-size:12px;
        font-weight:bold;
        text-transform:uppercase;
        cursor:pointer; 
        transition:all 0.3s ease-in;
    }
    li:hover{
        color:gray;
    }
    @media (max-width:840px){
    }
`