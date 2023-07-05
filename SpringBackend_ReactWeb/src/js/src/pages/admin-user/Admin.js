import { Component } from "react";
import Container from "../../Container";
import "../../../src/App.css";
import { getRankedUsers, getUserInfo } from "../../../src/client";
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
    loggedInUserId: "",
  };

  componentDidMount() {
    this.fetchUsers();
    this.fetchLoggedInUserId();
  }

  fetchUsers = () => {
    this.setState({
      isFetching: true,
    });

    getRankedUsers()
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

  fetchLoggedInUserId = () => {
    getUserInfo()
      .then((user) => {
        console.log(user);
        this.setState({
          loggedInUserId: user.id,
        });
      })
      .catch((error) => {
        console.log(error);
      });
  };

  render() {
    const { users, isFetching, loggedInUserId } = this.state;

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
          title: "Nume și prenume",
          key: "fullName",
          render: (user) => (
            <span
              className={user.id === loggedInUserId ? "logged-in-user-row" : ""}
            >
              {`${user.firstName} ${user.lastName}`}
            </span>
          ),
        },
        {
          title: "Județ",
          key: "state",
          render: (user) => (
            <span
              className={user.id === loggedInUserId ? "logged-in-user-row" : ""}
            >
              {user.state}
            </span>
          ),
        },
        {
          title: "Oraș",
          key: "city",
          render: (user) => (
            <span
              className={user.id === loggedInUserId ? "logged-in-user-row" : ""}
            >
              {user.city}
            </span>
          ),
        },
        {
          title: "Puncte",
          key: "points",
          render: (user) => (
            <span
              className={user.id === loggedInUserId ? "logged-in-user-row" : ""}
            >
              {user.points}
            </span>
          ),
        },
      ];

      return (
        <Container>
          <Table
            id="usersTable"
            dataSource={users}
            columns={columns}
            rowKey="id"
            pagination={false}
            style={{ margin: "80px" }}
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
