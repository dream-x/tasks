import React from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Contacts from './Contacts';

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route exact path="/" element={<Contacts />} />
      </Routes>
      </BrowserRouter>
  )
}

export default App