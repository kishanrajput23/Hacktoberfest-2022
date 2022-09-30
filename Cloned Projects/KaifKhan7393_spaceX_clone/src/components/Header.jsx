import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import { AiOutlineMenu } from 'react-icons/Ai';
import { FaTimes } from 'react-icons/Fa';

const HeaderData = [
    {
        id: 1,
        text: 'Falcon 9'
    },
    {
        id: 2,
        text: 'Falcon Heavy'
    },
    {
        id: 3,
        text: 'Dragon'
    },
    {
        id: 4,
        text: 'Startship'
    },
    {
        id: 5,
        text: 'Human SpaceFlight'
    },
    {
        id: 6,
        text: 'Rideshare'
    },
    {
        id: 7,
        text: 'Starlink'
    },
];

const BurgerData = [
    {
        id: 1,
        text: 'Mission'
    },
    {
        id: 2,
        text: 'Launches'
    },
    {
        id: 3,
        text: 'Careers'
    },
    {
        id: 4,
        text: 'Updates'
    },
    {
        id: 5,
        text: 'Shop'
    },
];

const combineData = [...HeaderData, ...BurgerData];

const Header = () => {

    const [showMenu, setShowMenu] = useState(false);
    const width = window.innerWidth;
    const [menuData, setMenuData] = useState(BurgerData);

    useEffect(() => {
        width < 840 ? setMenuData(combineData) : setMenuData(BurgerData);
    }, []);

    return (
        <Container>
            <WrapHeader>
                <LeftContainer>
                    <img src="https://upload.wikimedia.org/wikipedia/commons/9/96/SpaceX_Logo_Black.png" alt="" />
                    <LeftMenu>
                        {
                            HeaderData.map((curEle) => {
                                return (
                                    <li key={curEle.id}>{curEle.text}</li>
                                )
                            })
                        }
                    </LeftMenu>
                </LeftContainer>

                <RightContainer>
                    <li>Shop</li>
                    <AiOutlineMenu onClick={() => setShowMenu(true)} className="menuBar" />
                </RightContainer>

                <NavMenu show={showMenu}>
                    <NavMenuItems>
                        <FaTimes onClick={() => setShowMenu(false)} className="closeIcon" />
                        <NavMenuItemsData>
                            {
                                menuData.map((curEle) => {
                                    return (
                                        <div key={curEle.id}>
                                            <li key={curEle.id}>{curEle.text}</li>
                                            <hr />
                                        </div>
                                    )
                                })
                            }
                        </NavMenuItemsData>
                    </NavMenuItems>
                </NavMenu>
            </WrapHeader>
        </Container>
    )
}


const Container = styled.div`
    width: 100%;
    position:absolute;
    top:0;
    z-index:10;
`
const WrapHeader = styled.div`
    width:100%;
    height: 90px;
    color:white;
    display:flex;
    justify-content:space-between;
    align-items:center;
    padding:0 100px;
    position:relative;
    @media (max-width: 840px) {
        padding:0 15px;
    }
`

const LeftContainer = styled.div`
    width:1000px;
    display:flex;
    justify-content:space-between;
    align-items:center;
    img{
        width:16.5%;
        filter:invert(1);
        cursor:pointer;
    }
    @media (max-width: 840px) {
        img{
            width:65%;
            max-width:150px;
        }
    }
`

const LeftMenu = styled.ul`
    display:flex;
    list-style-type: none;
    align-items:center;
    li{
        margin-left:20px;
        list-style-type: none;
        text-transform:uppercase;
        color:white;
        transition:all 0.5s ease-in;
        font-size:12px;
        font-weight:800;
        padding-top:4px;
        cursor:pointer;
    }
    li:hover{
        border-bottom:1px solid white;
    }
    @media (max-width: 840px) {
        display:none;
    }
`

const RightContainer = styled.div`
    display:flex;
    position:absolute;
    right:100px;
    .menuBar{
        color:white;
        font-size:20px;
        margin-left:30px;
        padding-top:4px;
        cursor:pointer;
    }
    li{
        list-style: none;
        text-transform:uppercase;
        color:white;
        transition:all 0.5s ease-in;
        font-size:12px;
        font-weight:800;
        padding-top:4px;
        cursor:pointer;
    }
    li:hover{
        border-bottom:1px solid white;
    }
    @media (max-width: 840px) {
        right:20px;
    }
`

const NavMenu = styled.div`
    width:350px;
    height:100vh;
    background-color:black;
    position:absolute;
    top:0;
    transition:all 0.3s ease-in-out;
    right:${props => (props.show) ? '0' : '-350px'};
    z-index:10;
`

const NavMenuItems = styled.div`
    width:100%;
    ${'' /* color:white; */}
    display:flex;
    flex-direction:column;
    align-items:flex-end;
    padding:40px;
    .closeIcon{
        cursor:pointer;
    }
`
const NavMenuItemsData = styled.ul`
    width:100%;
    list-style:none;
    display:flex;
    flex-direction:column;
    align-items:flex-end;
    ${'' /* paddingn */}
    div{
        width:100%;
    }
    li{
        margin-top:20px;
        text-transform:uppercase;
        font-weight:300;
        font-size:14px;
        text-align:right;
        padding-bottom:10px;
    }
    hr{
        width:100%;
        height:.5;
        border-color:gray;
    }
`

export default Header