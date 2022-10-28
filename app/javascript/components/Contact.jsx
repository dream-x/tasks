import React, { useState, useEffect } from 'react'
import { useParams, useNavigate } from 'react-router-dom'

const Contact = () => {
  const { id } = useParams()
  const [contact, setContact] = useState()
  const [contactName, setContactName] = useState();
  const [contactJob, setContactJob] = useState();
  const [contactAge, setContactAge] = useState();
  const navigate = useNavigate()

  useEffect(() => {
    const getContact = async () => {
        const response = await fetch(`http://localhost:3000/api/v1/contacts/${id}.json`)
        const data = await response.json()
        setContact(data)
      }
    getContact()
  }, [])

  const updateContactInfo = async () => {
    const updatedContact = {
        name: contactName,
        age: contactAge,
        job: contactJob,
    }
    const config = {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedContact),
    }
    const response = await fetch(`http://localhost:3000/api/v1/contacts/${id}`, config)
    await response.json()
  }

  const onSubmit = (e) => {
    updateContactInfo()
    e.preventDefault()
    navigate(`/contacts/${id}`)
    window.location.reload()
  }

  const deleteContact = async () => {
    const config = {
        method: 'DELETE',
    }
    const response = await fetch(`http://localhost:3000/api/v1/contacts/${id}`, config)
    await response.json()
  }

  const onDelete = () => {
   deleteContact()
   navigate('/')
  }

  return (
    <div>
        <h4>This is contact number: {id}</h4>
        <div>
            {contact ?
            (
                <div>
                    <h2>Name is: {contact.name}</h2>
                    <h3>Job is: {contact.job}</h3>
                    <h5>Age is: {contact.age}</h5>
                </div>
            )
            :
            (
                <div>
                    <p>Loading!!</p>
                </div>
            )
            }
        </div>

        <p>Edit the contact here!</p>
        <form onSubmit={onSubmit}>
            <input type="text" name="name" placeholder="Name" onChange={(e) => setContactName(e.target.value)} required />
            <br />
            <input type="text" name="job" placeholder="Job" onChange={(e) => setContactJob(e.target.value)} required />
            <br />
            <input type="number" name="age" placeholder="Age" onChange={(e) => setContactAge(e.target.value)} required />
            <br />
            <button type='submit'>Edit</button>
        </form>

        <p>Delete contact here!</p>
        <button type='button' onClick={onDelete}>Delete</button>
    </div>
  )
}

export default Contact