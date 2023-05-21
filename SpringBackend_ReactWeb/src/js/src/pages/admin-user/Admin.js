import { Component } from "react";
import Container from "../../Container";
import "../../../src/App.css";
import { getAllUsers } from "../../../src/client";
import { Table, Avatar, Spin } from "antd";
import { LoadingOutlined } from "@ant-design/icons";
import Navbar from "../../sharepages/Navbar";
import Footer from "../../sharepages/Footer";
import { errorNote } from "../../Notification";

const getIcon = () => <LoadingOutlined style={{ fontSize: 24 }} spin />;

class Admin extends Component {
  state = {
    users: [],
    isFetching: false,
  };

  componentDidMount() {
    this.fetchUsers();
  }

  fetchUsers = () => {
    this.setState({
      isFetching: true,
    });

    getAllUsers()
      .then((res) =>
        res.json().then((users) => {
          console.log(users);
          this.setState({
            users,
            isFetching: false,
          });
        })
      )
      .catch((error) => {
        console.log(error.error.message);
        const message = error.error.message;
        const description = error.error.error;
        errorNote(message, description);
        this.setState({
          isFetching: false,
        });
      });
  };

  render() {
    const { users, isFetching } = this.state;

    if (isFetching) {
      return (
        <Container>
          <Spin indicator={getIcon()} />
        </Container>
      );
    }

    if (users && users.length) {
      const columns = [
        {
          title: "",
          key: "avatar",
          render: (user) => (
            <Avatar size="large">
              {`${user.firstName.charAt(0).toUpperCase()}${user.lastName
                .charAt(0)
                .toUpperCase()}`}
            </Avatar>
          ),
        },
        {
          title: "User ID",
          dataIndex: "id",
          key: "id",
        },
        {
          title: "Prenume",
          dataIndex: "firstName",
          key: "firstName",
        },
        {
          title: "Nume",
          dataIndex: "lastName",
          key: "lastName",
        },

        {
          title: "Email",
          dataIndex: "email",
          key: "email",
        },

        {
          title: "Adresă",
          dataIndex: "address",
          key: "address",
        },
      ];

      return (
        <Container>
          <Table
            dataSource={users}
            columns={columns}
            rowKey="id"
            pagination={false}
          />

          <Navbar></Navbar>
          <Footer></Footer>
        </Container>
      );
    }
    return <h1>Greenpanion Users doesn't exist!</h1>;
  }
}

export default Admin;
