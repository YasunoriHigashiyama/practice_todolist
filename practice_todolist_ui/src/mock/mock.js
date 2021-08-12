import axios from 'axios'
import MockAdapter from 'axios-mock-adapter';

const initialData = () => ({
    data: [
        { id: "202001011234", title: '猫に餌をあげる', start: '2020/01/01', limit: '9999/12/31', memo: "毎日あげること", status: false },
        { id: "202001098765", title: '猫と遊ぶ', start: '2020/01/01', limit: '2021/10/31', memo: "毎日遊ぶこと", status: false },
        { id: "201901011234", title: '猫を飼う', start: '2019/01/01', limit: '2020/01/01', memo: "なるべく早く飼うこと", status: true },
    ],
})
// option: { todo: true, done: true, sort: "ac", order: "start", number: "20" }
let data = initialData()
/* eslint no-unused-vars: 0 */
function urlCheck(path = '') {
    path = typeof path === 'string' ?
        new RegExp(path.replace(/:\w+/g, '[^/]+')) :
        path
    return path
}
export default {
    urlCheck,

    run: client => {
        const mock = new MockAdapter(axios)
        mock.onGet('/tasks').reply(config => {
            console.log("タスクの全取得")
            console.log(config.params)

            //タスクとってくる
            return [200, initialData()]
        }),
            mock.onPost('/create/Task').reply(config => {
                console.log("タスクの追加")
                console.log(config.data)
                //タスク追加する
                // const res = {
                //     data: [{ id: "202001011234", title: '猫に餌をあげる', start: '2020/01/01', limit: '9999/12/31', memo: "毎日あげること", status: false },
                //     { id: "202001098765", title: '猫と遊ぶ', start: '2020/01/01', limit: '2021/10/31', memo: "毎日遊ぶこと", status: false },
                //     { id: "201901011234", title: '猫を飼う', start: '2019/01/01', limit: '2020/01/01', memo: "なるべく早く飼うこと", status: true },
                //     { id: "201901011299", title: '追加した結果のダミータスク', start: '2019/01/01', limit: '2020/01/01', memo: "", status: false }]
                // };
                return [200]
            }),
            mock.onPost('/edit/Task').reply(config => {
                console.log("タスクの追加")
                console.log(config.data)
                //タスク追加する
                // const res = {
                //     data: [{ id: "202001011234", title: '猫に餌をあげる', start: '2020/01/01', limit: '9999/12/31', memo: "毎日あげること", status: false },
                //     { id: "202001098765", title: '猫と遊ぶ', start: '2020/01/01', limit: '2021/10/31', memo: "毎日遊ぶこと", status: false },
                //     { id: "201901011234", title: '猫を飼う', start: '2019/01/01', limit: '2020/01/01', memo: "なるべく早く飼うこと", status: true },
                //     { id: "201901011299", title: '追加した結果のダミータスク', start: '2019/01/01', limit: '2020/01/01', memo: "", status: false }]
                // };

                return [200]
            }),
            mock.onPost('/update/status').reply(config => {
                console.log("ステータスの更新")
                console.log(config.data)
                //タスク追加する
                // const res = {
                //     data: [{ id: "202001011234", title: '猫に餌をあげる', start: '2020/01/01', limit: '9999/12/31', memo: "毎日あげること", status: false },
                //     { id: "202001098765", title: '猫と遊ぶ', start: '2020/01/01', limit: '2021/10/31', memo: "毎日遊ぶこと", status: false },
                //     { id: "201901011234", title: '猫を飼う', start: '2019/01/01', limit: '2020/01/01', memo: "なるべく早く飼うこと", status: true },
                //     { id: "201901011299", title: '追加した結果のダミータスク', start: '2019/01/01', limit: '2020/01/01', memo: "", status: false }]
                // };
                // return [200, res]
                // let id = null;
                // for (var i in res.data) {
                //     if (res.data[i].id == config.data.id) {
                //         res.data[i].status = config.data.status
                //         return [200, res]
                //     }
                // }
                // if (id == null) {
                //     return [200, res];
                // }
                return [200]
            }),

            mock.onPost('/delete/task').reply(config => {
                console.log("タスクの削除")
                // var res = {
                //     data: [
                //         { id: "202001011234", title: '猫に餌をあげる', start: '2020/01/01', limit: '9999/12/31', memo: "毎日あげること", status: false },
                //         { id: "202001098765", title: '猫と遊ぶ', start: '2020/01/01', limit: '2021/10/31', memo: "毎日遊ぶこと", status: false },
                //         { id: "201901011234", title: '猫を飼う', start: '2019/01/01', limit: '2020/01/01', memo: "なるべく早く飼うこと", status: true },
                //         { id: "201901011299", title: '追加した結果のダミータスク', start: '2019/01/01', limit: '2020/01/01', memo: "", status: false },
                //     ]
                // }
                // let id = null;

                // for (var i in res.data) {
                //     if (res.data[i].id == config.data) {
                //         res.data.splice(i, 1);
                //         return [200, res]
                //     }
                // }
                // if (id == null) {
                //     return [200, res];
                // }
                return [200]
            })

    },
    getData: () => {
        return JSON.parse(JSON.stringify(data))
    },
    setData: input => {
        data = JSON.parse(JSON.stringify(input))
    },
    initialize: () => {
        data = initialData()
    },
}