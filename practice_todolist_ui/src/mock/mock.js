import axios from 'axios'
import MockAdapter from 'axios-mock-adapter';

const initialData = () => ({
    data: [
        { id: "202001011234", title: '猫に餌をあげる', start: '2020/01/01', limit: '9999/12/31', memo: "毎日あげること", status: false },
        { id: "202001098765", title: '猫と遊ぶ', start: '2020/01/01', limit: '2021/10/31', memo: "毎日遊ぶこと", status: false },
        { id: "201901011234", title: '猫を飼う', start: '2019/01/01', limit: '2020/01/01', memo: "なるべく早く飼うこと", status: true },
        { id: "202101011235", title: 'test1', start: '2019/01/01', limit: '2020/01/01', memo: "", status: false },
        { id: "202101011236", title: 'test2', start: '2019/01/01', limit: '2020/01/01', memo: "", status: false },
        { id: "202101011237", title: 'test3', start: '2019/01/01', limit: '2020/01/01', memo: "", status: false },
        { id: "202101011236", title: 'test4', start: '2019/01/01', limit: '2020/01/01', memo: "", status: false },
        { id: "202101011236", title: 'test5', start: '2019/01/01', limit: '2020/01/01', memo: "", status: false },
        { id: "202101011236", title: 'test6', start: '2019/01/01', limit: '2020/01/01', memo: "", status: false },
        { id: "202101011236", title: 'test7', start: '2019/01/01', limit: '2020/01/01', memo: "", status: false },
        { id: "202101011236", title: 'test8', start: '2019/01/01', limit: '2020/01/01', memo: "", status: false },
        { id: "202101011236", title: 'test9', start: '2019/01/01', limit: '2020/01/01', memo: "", status: false },
        { id: "202101011236", title: 'test10', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test11', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test12', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test13', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test14', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test15', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test16', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test17', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test18', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test19', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test20', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test21', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test22', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test23', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test24', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test25', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test26', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test27', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test28', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test29', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test30', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test31', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test32', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test33', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test34', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test35', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test36', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test37', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test38', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
        { id: "202101011236", title: 'test39', start: '2019/01/01', limit: '2020/01/01', memo: "", status: true },
    ],
    paging: { total: 42 }

})
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
        mock.onGet('/task').reply(config => {
            // console.log("タスクの全取得")
            // console.log(config.params)
            // console.log("表示数:" + config.params.number)
            // console.log("ページnumber:" + config.params.page)
            let init = initialData()

            let dataList = []

            if (config.params.done) {
                //タスク完了済み
                for (var i of init.data) {
                    if (i.status) {
                        dataList.push(i)
                    }
                }
            }
            if (config.params.todo) {
                //タスク未完了
                for (var j of init.data) {
                    if (!j.status) { dataList.push(j) }
                }
            }

            let res = {}
            res["paging"] = { total: dataList.length }
            let startIndex = config.params.number * (config.params.page - 1)
            let endIndex = (config.params.number * config.params.page)
            console.log("startIndex:" + startIndex)
            console.log("endIndex:" + endIndex)
            console.log(dataList.length)
            if (dataList.length < startIndex) { res["data"] = dataList } else {
                res["data"] = dataList.slice(startIndex, endIndex)
            }
            console.log(res["data"].length)
            //タスクとってくる
            return [200, res]
        }),
            mock.onPost('/task').reply(config => {
                console.log("タスクの追加")
                console.log(config)

                return [200]
            }),
            mock.onPut(urlCheck('/task/:id')).reply(config => {
                console.log("タスクの更新")
                console.log(config.data)

                return [200]
            }),
            mock.onPatch(urlCheck('/task/:id/status')).reply(config => {
                console.log("ステータスの更新")
                console.log(config.data)

                return [200]
            }),

            mock.onDelete(urlCheck("/task/:id")).reply(config => {
                console.log("タスクの削除")
                console.log(config)

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