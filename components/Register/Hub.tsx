import React, { useState } from 'react';

import { postService } from 'services/post/postService';
import { Informations } from "./Informations"
import { Localization } from "./Localization"
import { Type } from "./Type"
import { SellOrRent } from "./SellOrRent"

export const Hub = () => {
  const [ step, setStep ] = useState("Hub");
  const [ type, setType ] = useState("");
  const [ title, setTitle ] = useState("");
  const [ restrooms, setRestrooms ] = useState();
  const [ bedrooms, setBedrooms ] = useState();
  const [ size, setSize ] = useState();
  const [ image, setImage ] = useState();
  const [ price, setPrice ] = useState();
  const [ body, setBody ] = useState();
  const [ address, setAddress ] = useState();
  const [ location, setLocation ] = useState({lat: null, lng: null})


  function onHandleSubmitDone(e:any) {
    e.preventDefault();

    console.log("DADOS: ", {location, title, restrooms, bedrooms, size, image, price, body, address})

    const longitude = location.lng;
    const latitude = location.lat;
    const status = type;

    postService.createPost({title, body, image, price, size, restrooms, bedrooms, longitude, latitude, status});
  }

  if(step === "Localization") {
    return(
      <Localization setLocation={setLocation} setStep={setStep} setAddress={setAddress} address={address} />
    )
  }

  if(step === "Informations") {
    return (
      <Informations setRestrooms={setRestrooms} restrooms={restrooms} setBedrooms={setBedrooms} bedrooms={bedrooms} setImage={setImage} image={image} setPrice={setPrice} price={price} setBody={setBody} body={body} setTitle={setTitle} title={title} type={type} setSize={setSize} size={size} setStep={setStep} onHandleSubmitDone={onHandleSubmitDone}/>
    )
  }

  if(step === "Type") {
    return(
      <Type setType={setType} type={type} setStep={setStep}/>
    )
  }

  if(step === "SellOrRent") {
    return(
      <SellOrRent setType={setType} type={type} setStep={setStep}/>
    )
  }

  return(
    <div>
        <div className="max-w-7xl mx-auto items-center flex w-full justify-center mt-32">
          <div className="flex-col">
            <div className="flex">
              <div className="w-full items-center justify-center text-xl">
                <p className="">Etapa 1</p>
                <h1 className="font-bold text-5xl mt-5">Descreva sua acomodação</h1>
                <div className="mt-10">
                  <p>Nessa etapa, perguntaremos que tipo de propriedade você deseja anunciar e se os hóspedes poderão reservar o espaço inteiro ou apenas um quarto. Em seguida, informe a localização e quantas pessoas podem se hospedar.</p>

                </div>
              </div>
              <div className="flex w-full items-center justify-center text-xl">
                <img src="https://w7.pngwing.com/pngs/981/116/png-transparent-brown-size-chart-perspective-perspective-isometric-view-3d-size-chart-stereo-kitchen.png" />
              </div>
            </div>
            <div className="mt-20 flex flex-col text-center">
              <p className="bg-black max-w-fit text-white rounded-lg p-5 cursor-pointer" onClick={() => setStep("SellOrRent")}>Avancar</p>
            </div>
          </div>

        </div>

      
    </div>
    
  )
}