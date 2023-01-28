import React, { useEffect, useState } from 'react'
import Link from "next/link";
import Image from 'next/image';
import { tokenService } from 'services/auth/tokenService';
import { selectAuthState, setAuthState } from "../../src/store/authSlice";
import { useSelector, useDispatch } from "react-redux";
import Logo from "../../public/Logo.png"
import {FaBars} from "react-icons/fa";
import {MdClose} from "react-icons/md";
import {AiOutlineArrowDown} from "react-icons/ai"
import Popup from 'reactjs-popup';
import Skeleton from '@mui/material/Skeleton';
import { useNotification } from "use-toast-notification";
import { Toast } from 'services/notification/toast';
import { cleanUserData, selectUserData } from '../../src/store/userSlice';
import { selectLoading } from '@/store/loadingSlice';
import {SearchIcon} from "@heroicons/react/solid"

export const Header = () => {
  const [barsOpen, setBarsOpen] = useState(false);
  const dispatch = useDispatch();
  const notification = useNotification();
  const authState = useSelector(selectAuthState)
  const user = useSelector(selectUserData)
  const loadingState = useSelector(selectLoading)

  function signOut(e) {
    e.preventDefault();

    dispatch(setAuthState(false))
    dispatch(cleanUserData())

    tokenService.delete(null);
    Toast.notifySuccess(notification, "Logout Sucess!", "You have logged out!")
    setBarsOpen(false);
  }

  if(barsOpen === true) {
    return (
      <div className="text-center flex h-screen justify-center items-center flex-col">
        <div>
          <p className="navResponsiveButtonClose" onClick={() => setBarsOpen(false)}><MdClose size={25}/></p>

        </div>
        <div className="flex flex-col">
          <p className="navResponsiveButton">Registre um apartamento</p>
          <p className="navResponsiveButton">Conta</p>
        </div>
      </div>
      
    )
  }

  return (
    <div className="sticky flex justify-between top-0 z-100 bg-white px-4 py-5 shadow-sm items-center ">
      <div className="relative h-13 w-13 flex-shrink-0 cursor-pointer">
        <Link href="/"><Image src={Logo} width={150} height={150} alt="RealStateLogo" /></Link>
      </div>

      <form className="max-w-lg flex items-center sm:space-x-2 border-gray-200 border rounded-3xl bg-gray-100 sm:flex-1 px-7 py-2 lg:ml-10">
        <input type="text" placeholder="Begin your search" className="flex-1 bg-transparent outline-none" />
        <Link href={`/search/`}>
            <button type="submit" hidden/>
        </Link>
        <div className="bg-red-400 rounded-full p-2">
          <SearchIcon className="h-5 w-5 text-white"/>
        </div>
      </form>

      <div className="navButton">
        <p className="">Registre um apartamento</p>
      </div>
      <div className="navButton">
      </div>

      <div onClick={() => setBarsOpen(true)} className="lg:hidden md:hidden flex w-10 cursor-pointer">
        <FaBars size={25}/>
      </div>

      {
        authState?
          <Popup
          trigger={
          <div onClick={() => signOut()} className="navButton">
              <div className="flex h-11 w-11 -mr-2">
                {loadingState?
                  <Skeleton variant="circular" width={40} height={40} />
                :
                  <img className="rounded-full object-cover" src={user.image} alt="imgProfile"/>
                }
              </div>
              <AiOutlineArrowDown className="-ml-3"/>
          </div>}
          position="bottom center"
          nested
          >
            <div className="navPopupAccount">
              <Link href={`/profile/${user.id}`}><p className="navButtonAccount">Conta</p></Link>
              <p className="navButtonAccount">Salvos</p>
              <p className="navButtonAccount" onClick={(e) => signOut(e)}>Logout</p>
            </div>
          </Popup>
        : 
          <div className="navButton">
              <div>
                <Link href="/login"><p className="text-black">Sign In</p></Link>
              </div>

          </div>
      }
    </div>
  )
}
