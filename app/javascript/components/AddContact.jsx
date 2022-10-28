import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom';

const AddContact = () => {
  const [name, setName] = useState()
  const [job, setJob] = useState()
  const [age, setAge] = useState()

  const navigate = useNavigate()

  const postContactInfo = async () => {
    const contact = {
        name,
        age,
        job,
    }
    const config = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(contact),
    }
    const response = await fetch('http://localhost:3000/api/v1/create/contact/', config)
    await response.json()
  }

  const onSubmit = (e) => {
    postContactInfo()
    e.preventDefault()
    navigate('/')
  }

  return (
    <div>
        <form onSubmit={onSubmit}>
            <input type="text" name="name" placeholder="Name" onChange={(e) => setName(e.target.value)} required />
            <br />
            <input type="text" name="job" placeholder="Job" onChange={(e) => setJob(e.target.value)} required />
            <br />
            <input type="number" name="age" placeholder="Age" onChange={(e) => setAge(e.target.value)} required />
            <br />
            <button type='submit'>Add Contact</button>
        </form>
    </div>
  )
}

export default AddContact